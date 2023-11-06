
const PAGE_NUMBER = 4;
const PAGE_SIZE = 16
const domParser = new DOMParser();
let currentPage = 0;
let totalPage = 0;

const JOB_CARD_TEMPLATE = `
            <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12 mb-5">
                          <figure class="effect-ming tm-video-item">
                              <img src="#{COMPANY_LOGO}#" alt="Image" class="img-fluid">
                              <figcaption class="d-flex align-items-center justify-content-center">

                                  <h2><span>잡플래닛 평점</span>
                                      <br/>
                                      <span>#{JOBPLANET_REVIEW}#</span>
                                  </h2>
                                  <a href=#{WANTED_ORIGIN_LINK}#>View more</a>
                              </figcaption>
                          </figure>
                          <div class="d-flex justify-content-between tm-text-gray">
                              <span class="tm-text-gray-light">#{CREATE_YEAR}#</span>
                              <span>#{JOB_POSITION}#</span>
                              <span><a href=#{JOBPLANET_ORIGIN_LINK}#>#{COMPANY_NAME}#</a></span>
                          </div>
              </div>
    `
const PAGE_TEMPLATE = `<a class="tm-paging-link" data-value="#{PAGE_NUM}#">#{PAGE_NUM}#</a>`

window.addEventListener("load", () => {
    sendPagingRequest(0)
    document.body.classList.add("loaded");
});

const paging = document.querySelector('.tm-paging');
for (let i = 0; i < PAGE_NUMBER; i++) {
    const pageHTML = PAGE_TEMPLATE.replaceAll("#{PAGE_NUM}#", i + 1);
    const pageElement = domParser.parseFromString(pageHTML, 'text/html').body.firstChild;
    paging.appendChild(pageElement);
}

const prevBtn = document.querySelector('.tm-btn-prev');
prevBtn.addEventListener('click', () => {
        if (currentPage <= 0) {
            currentPage = 0;
            prevBtn.classList.add("disabled");
            return;
        }
        nextBtn.classList.remove("disabled");
        currentPage--;
        sendPagingRequest(currentPage)
    }
)
const nextBtn = document.querySelector('.tm-btn-next');
nextBtn.addEventListener('click', () => {
        if (currentPage + 1 >= totalPage) {
            currentPage = totalPage;
            nextBtn.classList.add("disabled");
            return;
        }
        prevBtn.classList.remove("disabled")
        currentPage++;
        sendPagingRequest(currentPage)
    }
)

document.querySelector("#search-btn")
    .addEventListener('click',()=>{
        searchJobRequest();
    })

function sendPagingRequest(page) {
    axios.get("/api/recruitments", {
        params: {
            page: page,
            pageSize: PAGE_SIZE,
        },
    }).then((resp) => {
        const data = resp.data;
        const jobCardContainer = document.querySelector("#list-container");
        jobCardContainer.textContent = '';
        data.content.forEach((job) => {
                const averageScore = job.jobPlanetScore === null ? "확인불가" : job.jobPlanetScore.slice(0, 3);
                const jobPlanetOriginLink = job.jobPlanetOriginLink === null ? "javascript:void(0);" : job.jobPlanetOriginLink;
                const jobCardFragment = JOB_CARD_TEMPLATE.replace("#{COMPANY_LOGO}#", job.companyLogoPath)
                    .replace("#{JOB_POSITION}#", job.jobPosition)
                    .replace("#{WANTED_ORIGIN_LINK}#", job.wantedOriginLink)
                    .replace("#{CREATE_YEAR}#", job.createYear)
                    .replace("#{COMPANY_NAME}#", job.companyName)
                    .replace("#{JOBPLANET_REVIEW}#", averageScore)
                    .replace("#{JOBPLANET_ORIGIN_LINK}#", jobPlanetOriginLink);
                const jobCardElement = domParser.parseFromString(jobCardFragment, 'text/html').body.firstChild;
                jobCardContainer.appendChild(jobCardElement);
            }
        )
        currentPage = data.pageable.pageNumber
        totalPage = data.totalPages;

        const firstPageNumber = Number(paging.firstElementChild.getAttribute('data-value'));
        const lastPageNumber = firstPageNumber + PAGE_NUMBER;
        if (firstPageNumber > currentPage + 1) {
            paging.textContent = '';
            const nextBound = currentPage + 1 - PAGE_NUMBER
            for (let i = currentPage + 1; i > nextBound && i >= 0; i--) {
                const pageHTML = PAGE_TEMPLATE.replaceAll("#{PAGE_NUM}#", i);
                const pageElement = domParser.parseFromString(pageHTML, 'text/html').body.firstChild;
                paging.prepend(pageElement);
            }
        }
        if (lastPageNumber <= currentPage + 1) {
            paging.textContent = '';
            const nextBound = currentPage + 1 + PAGE_NUMBER
            for (let i = currentPage + 1; i < nextBound && i <= totalPage; i++) {
                const pageHTML = PAGE_TEMPLATE.replaceAll("#{PAGE_NUM}#", i);
                const pageElement = domParser.parseFromString(pageHTML, 'text/html').body.firstChild;
                paging.appendChild(pageElement);
            }
        }
        paging.querySelectorAll('a').forEach((dom) => {
            if (Number(dom.getAttribute("data-value")) === (currentPage + 1)) {
                dom.classList.add('active');
            } else {
                dom.classList.remove('active');
            }
        });
        document.querySelectorAll(".tm-paging-link")
            .forEach((dom) => {
                dom.addEventListener('click', () => {
                    sendPagingRequest(dom.getAttribute("data-value") - 1)
                    document.querySelectorAll(".tm-paging-link").forEach((dom) => dom.classList.remove("active"));
                    dom.classList.add("active");
                })
            });
    });
}


function searchJobRequest(){
    const elements = document.querySelectorAll("#filters .form-check-input:checked");
    const checkFilters = Array.from(elements).map(element => element.value);
    const searchInput = document.querySelector("#search-input");

    axios.post("/api/recruitments",{
        'searchTerm': searchInput.value,
        'filters': checkFilters,
    })
    .then((data)=>console.log(data));
}
