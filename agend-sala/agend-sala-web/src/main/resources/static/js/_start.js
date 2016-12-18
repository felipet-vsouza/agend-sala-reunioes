var main = {};

main.defineDefaultLoader = function () {
    $.ajaxPrefilter((options, _, jqXHR) => {
        main.toggleScroll();
        main.toggleLoader();
        jqXHR.done(() => {
            main.toggleScroll();
            main.toggleLoader();
        }).fail(() => {
            main.toggleScroll();
            main.toggleLoader();
        });
    });
}

main.defineReponsiveNavToggle = function () {
    $('#nav-toggler').click(function () {
        $('#nav-to-toggle').toggleClass('is-active');
        $('#nav-toggler').toggleClass('is-active');
    });
}

main.toggleScroll = function () {
    let windowEvents = jQuery._data(window, "events");
    if (!!windowEvents && !!windowEvents.mousewheel) {
        $(window).unbind("mousewheel");
    } else {
        $(window).bind("mousewheel", function (event) {
            event.preventDefault();
        });
    }
}

main.toggleLoader = function () {
    $('.is-loader')
        .toggleClass('is-loading')
        .toggleClass('is-disabled');
}

$(function () {
    main.defineReponsiveNavToggle();
    main.defineDefaultLoader();
    new Home();
});

class Home {
    constructor() {
        this.modal = $('.modal');
        this.modalContent = $('.modal-content');
        this.modalClose = $('.modal-close');
        this.defineFiltroSalasBind();
        this.defineAgendamentoSalasBind();
        this.defineModalBinds();
    }

    defineFiltroSalasBind() {
        let self = this;
        this.filtroButton = $('#but-filtro');
        this.filtroButton.click(function () {
            $.get('/home/salas')
                .then(res => {
                    self.modal.css("display", "flex");
                    self.modalContent.html(res);
                    new FiltroSalas();
                })
                .fail(err => {
                    self.modal.css("display", "flex");
                    self.modalContent.html(err);
                });
        });
    }
        defineAgendamentoSalasBind() {
        let self = this;
        this.filtroButton = $('#but-agendamento');
        this.filtroButton.click(function () {
            $.get('/home/agendamento')
                .then(res => {
                    self.modal.css("display", "flex");
                    self.modalContent.html(res);
                    new FiltroSalas();
                })
                .fail(err => {
                    self.modal.css("display", "flex");
                    self.modalContent.html(err);
                });
        });
    }

    defineModalBinds() {
        let self = this;
        this.modalClose.click(function () {
            self.modal.toggle();
        });
    }
}