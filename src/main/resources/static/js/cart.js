$(function () {

    // 전체 금액 계산
    let total = 0;
    $('tr.cart').each(function() {
        const price = $(this).find('input.price-input').val();
        const qty = $(this).find('input.qty-input').val();
        total += price * qty;
    });
    $('#total').text(total.toLocaleString() + "원");

})