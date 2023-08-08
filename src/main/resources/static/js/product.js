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
            maxPrice : maxPrice === null ? $('#maxPrice').val() : maxPrice,
            minPrice : minPrice === null ? $('#minPrice').val() : minPrice
        },
        contentType: "application/json; charset=utf-8",
        success:function (res) {
            $('#product-list').replaceWith(res);
            
            // Todo 없으면 표시해주기
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