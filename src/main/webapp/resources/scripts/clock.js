window.onload = function () {
    $('#clock').html(moment().format('D.MM.YYYY HH:mm:ss'));
}
setInterval(() => {
    $('#clock').html(moment().format('D.MM.YYYY HH:mm:ss'))
}, 5000);