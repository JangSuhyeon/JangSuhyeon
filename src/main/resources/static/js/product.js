// 상품 목록 reload
function reloadProductList(checkedBrand) {
    console.log(checkedBrand);
    $.ajax({
        type:"GET",
        url:"/shop/product/json",
        data:{ checkedBrand: JSON.stringify(checkedBrand) },
        contentType: "application/json; charset=utf-8",
        success:function (res) {
            console.log(res);
            // Todo 상품 목록 출력
            $('#product-list').replaceWith(res);
        },
        error:function () {
            console.error("Failed to reload the product list.")
        }
    });
}

$(function () {

    // 브랜드 체크시 이벤트
    $("input[name='brandCheckbox']").change(function () {
        // 체크된 브랜드 모으기
        const brandCheckbox = $('[name="brandCheckbox"]');
        const checkedBrand = [];

        brandCheckbox.each(function () {
            if ($(this).prop('checked')) {
                checkedBrand.push($(this).val());
            }
        });

        // 상품 목록 reload
        reloadProductList(checkedBrand);
    })

})