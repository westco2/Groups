$("#leftside-navigation .sub-menu > a").click(function(e) {
    $("#leftside-navigation ul ul").slideUp(), $(this).next().is(":visible") || $(this).next().slideDown(),
        e.stopPropagation()
})




$(".note-content-left").on('click',".note-text-wrap",function(e){
    console.log( $(this).closest(".note-content-left").find(".note-act"))
    $(this).closest(".note-content-left").find(".note-act").find("i").removeClass("hidden-m");
    $(this).closest(".note-content-left").find(".note-act").find("p").addClass("hidden-m");
    $(this).closest(".note-content-left").find(".note-act").removeClass("note-act");
    $(e.currentTarget).addClass("note-act");
    $(e.currentTarget).find("i").addClass("hidden-m");
    $(e.currentTarget).find("p").removeClass("hidden-m");


})

$(".co-flex").on('click',".desc-tr" ,function(e) {
    console.log($(e.currentTarget));
    $(this).closest(".co-flex").find(".i-col").removeClass("i-col");
    $(e.currentTarget).find("i").addClass("i-col");
});

document.getElementById("medbtn-m").addEventListener('click', ()=>{

    $("#modalfour").removeClass('hidden');
})
document.getElementById("close-m").addEventListener('click', ()=>{
    $("#modalfour").addClass('hidden');

})

/*
var swiper = new Swiper(".mySwiper", {
    slidesPerView: 4,
    spaceBetween: 30,
    centeredSlides: true,

    autoplay: {
        delay: 4500,
        disableOnInteraction: false,
    },
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    }
});*/
