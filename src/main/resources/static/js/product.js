// 상품 목록 reload
function reloadProductList(maxPrice, minPrice) {

    // 체크된 브랜드 모으기
    const brandCheckbox = $('[name="brandCheckbox"]');
    const checkedBrand = [];
    brandCheckbox.each(function () {
        if ($(this).prop('checked')) {
            checkedBrand.push($(this).val());
        }
    });

    $.ajax({
        type:"GET",
        url:"/shop/product/json",
        data:{
            cateId : $('#currentCategory').val(),
            checkedBrand: JSON.stringify(checkedBrand),
            maxPrice : typeof maxPrice === 'undefined' ? $('#maxPrice').val() : maxPrice,
            minPrice : typeof minPrice === 'undefined' ? $('#minPrice').val() : minPrice
        },
        contentType: "application/json; charset=utf-8",
        success:function (res) {
            var productList = $('#product-list');
            productList.replaceWith(res);

            // 상품이 없으면 안내 문구
            var updatedProductList = $('#product-list');
            if (updatedProductList.children().length <= 0) {
                var noProductInfo = $('<p class="noProductInfo">').text('상품이 없습니다.');
                updatedProductList.append(noProductInfo);
            }
        },
        error:function () {
            console.error("Failed to reload the product list.")
        }
    });
}

$(function () {

    // 브랜드 체크 이벤트
    $("input[name='brandCheckbox']").change(function () {
        // 상품 목록 reload
        reloadProductList();
    })

})