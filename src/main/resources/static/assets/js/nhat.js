let currentPage = 1;
let dimension = "asc";
let keySearch = "";
const pageable = {
    currentPage: currentPage,
    sortBy: "price",
    dimension: dimension,
    keySearch: keySearch,
    limit: 2
}


let productId = 0;
let product = null;
page.commands.renderPage = (page) => {
    return `<ul class="pagination justify-content-end">
                                <li class="page-item">
                                    <a class="page-link" id="prevBtn">Previous</a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">${page.number + 1}</a></li>
                                <li class="page-item">
                                    <a class="page-link" id="nextBtn">Next</a>
                                </li>
                            </ul>`;
}

page.commands.getAllProductPage = (pageable) => {
    page.elements.tbProduct.empty();
    $.ajax({
        headers: {
            'accept': 'application/json',
            'content-type': 'application/json'
        },
        type: 'POST',
        url: 'http://localhost:28002/api/products/pageable',
        data: JSON.stringify(pageable)
    })
        .done((data) => {
            page.elements.tbProduct.empty();
            const products = data.content;
            products.forEach(item => {
                const str = page.commands.renderProduct(item);
                page.elements.tbProduct.prepend(str);
            });

            const totalPages = data.totalPages;
            page.commands.updatePagination(currentPage, totalPages);
        })
        .fail((error) => {
            console.log(error);
        })

}
page.commands.updatePagination = (currentPage, totalPages) => {
    // Cập nhật trạng thái nút "Previous"
    if (currentPage === 1) {
        $('#prevBtn').prop('disabled', true);
    } else {
        $('#prevBtn').prop('disabled', false);
    }

    // Cập nhật trạng thái nút "Next"
    if (currentPage === totalPages) {
        $('#nextBtn').prop('disabled', true);
    } else {
        $('#nextBtn').prop('disabled', false);
    }
}


/*
let currentPage = 0;
page.commands.getAllProductPage = (pageable) => {
    page.elements.tbProduct.empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:28002/api/products'
    })
        .done((data) => {
            const content = data.content;
            content.forEach(item => {

                const str = page.commands.renderProduct(item);
                page.elements.tbProduct.prepend(str);
            });

            const totalPages = data.totalPages;
            updatePagination(currentPage, totalPages);
        })
        .fail((error) => {
            console.log(error);
        })

}

function updatePagination(currentPage, totalPages) {
    // Cập nhật trạng thái nút "Previous"
    if (currentPage === 0) {
        $('#prevBtn').prop('disabled', true);
    } else {
        $('#prevBtn').prop('disabled', false);
    }

    // Cập nhật trạng thái nút "Next"
    if (currentPage === totalPages - 1) {
        $('#nextBtn').prop('disabled', true);
    } else {
        $('#nextBtn').prop('disabled', false);
    }
}

// Xử lý sự kiện khi nhấp vào nút "Previous"
$('#prevBtn').on('click', () => {
    if (currentPage > 0) {
        currentPage--;
        page.commands.getAllProductPage(currentPage);
    }
});

// Xử lý sự kiện khi nhấp vào nút "Next"
$('#nextBtn').on('click', () => {
    currentPage++;
    page.commands.getAllProductPage(currentPage);
});

// Gọi lần đầu để lấy dữ liệu cho trang đầu tiên
page.commands.getAllProductPage(currentPage)
*/