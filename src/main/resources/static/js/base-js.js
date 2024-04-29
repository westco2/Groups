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

$(".barbar-xx").on("click", function (e) {
    var navbar = $("#navbar");
    $(this).closest(".nono-m").addClass("lld-m");
    navbar.addClass("hovertime");
    $(".link-text").addClass("link-textdel");
    $(this).css("visibility", "hidden");
    $(".vlvl-xx").css("display", "block");
});

$(".vlvl-xx").on("click", function (e) {
    var navbar = $("#navbar");
    $(this).closest(".nono-m").removeClass("lld-m");
    navbar.removeClass("hovertime");
    $(".link-text").removeClass("link-textdel");
    $(".barbar-xx").css("visibility", "visible");
    $(this).css("display", "none");
});

$(".navbar-items").on('click', "a", function (){
    $(this).closest(".navbar-items").find(".navck-m").removeClass("navck-m");
    $(this).addClass("navck-m");
});

