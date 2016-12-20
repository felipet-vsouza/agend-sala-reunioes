// funções iniciais
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


// validações
var parseBrazilianDate = function (content) {
    let from = content.split("/");
    return new Date(from[2], from[1] - 1, from[0]);
}

var parseHour = function (content) {
    let hour = parseInt(content.substring(0, 2));
    let minute = parseInt(content.substring(3, 5));
    hour += minute / 60;
    return hour;
}

$.validator.addMethod("anyDate",
    function (value, element) {
        return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[/., -](0?[1-9]|1[0-2])[/., -](19|20)?\d{2}$/);
    },
    "Por favor, insira uma data válida."
);

$.validator.addMethod("afterCurrentDay",
    function (value, element) {
        let atual = new Date();
        atual.setHours(0);
        atual.setMinutes(0);
        atual.setSeconds(0);
        atual.setMilliseconds(0);
        let selecionada = parseBrazilianDate(value);
        return (selecionada >= atual);
    },
    "Por favor, selecione uma data após a atual."
);

$.validator.addMethod("anyHour",
    function (value, element) {
        return value.match(/^([0-1]?[0-9]|2[0-4]):([0-5][0-9])(:[0-5][0-9])?$/);
    },
    "Por favor, insira um horário válido."
);

$.validator.addMethod("validHour",
    function (value, element) {
        let hour = value.substring(0, 2);
        return (hour >= 8 && hour <= 22);
    },
    "Por favor, insira um horário entre 8h e 22h."
);

$.validator.addMethod('competesWithOtherHour',
    function (value, element, params) {
        var initial = parseHour($(`input[name="${params[0]}"]`).val()),
            final = parseHour($(`input[name="${params[1]}"]`).val());
        return (initial < final);
    },
    "A hora inicial deve ser anterior à final."
);


// classes de telas
class Home {
    constructor() {
        this.modal = $('.modal');
        this.modalContent = $('.modal-content');
        this.modalClose = $('.modal-close');
        this.reunioes = $('#pn-reunioes');
        this.defineFiltroSalasBind();
        this.defineAgendamentoSalasBind();
        this.defineDetalheAgendamentosBind();
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
                    console.error(err);
                });
        });
    }

    defineDetalheAgendamentosBind() {
        let self = this;
        $('.is-agendamento').click(function () {
            let agendamento = $(this);
            let id = agendamento.find('.is-id').val();
            $.get(`/agendamento/detalhes/${id}`)
                .then(res => {
                    self.modal.css("display", "flex");
                    self.defineModalContent(res);
                    $('#agend-cancela').click(function () {
                        new PopupConfirmation({
                            onAgreed: function () {
                                $.post(`/agendamento/cancelar/${id}`)
                                    .then(res => {
                                        self.defineModalContent(res);
                                        self.refreshPainelReunioes()
                                    })
                                    .fail(err => {
                                        console.error('Erro na requisição: ', err);
                                    });
                            },
                            onDisagreed: function () {
                                self.modal.toggle();
                            },
                            container: self
                        });
                    });
                    $('#agend-altera').click(function () {
                        let id = parseInt($('#ag-id').val());
                        $.get(`/home/alteracao/${id}`)
                            .then(res => {
                                self.defineModalContent(res);
                                new AlteracaoSalas(id);
                            })
                            .fail(err => {
                                console.error('Erro na requisição: ', err);
                            });
                    });
                })
                .fail(err => {
                    console.error(err);
                });
        });
    }

    defineAgendamentoSalasBind() {
        let self = this;
        this.filtroButton = $('#but-agendamento');
        this.filtroButton.click(function () {
            $.post('/home/agendamento')
                .then(res => {
                    self.modal.css("display", "flex");
                    self.modalContent.html(res);
                    new AgendamentoSalas(self);
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

    defineModalContent(content) {
        this.modalContent.html(content);
    }

    refreshPainelReunioes() {
        let self = this;
        $.get('/home/reunioes')
            .then(res => {
                self.reunioes.html(res);
                self.defineDetalheAgendamentosBind();
            })
            .fail(err => {
                console.error('Erro na requisição: ', err);
            });
    }
}

class PopupConfirmation {
    constructor(configurations) {
        this.onAgreed = configurations.onAgreed;
        this.onDisagreed = configurations.onDisagreed;
        this.container = configurations.container;
        this.showPopupAndSetBinds();
    }

    showPopupAndSetBinds() {
        let self = this;
        $.get('/home/confirmation')
            .then(res => {
                self.container.defineModalContent(res);
                $('#conf-sim').click(self.onAgreed);
                $('#conf-nao').click(self.onDisagreed);
            })
            .fail(err => {
                console.error('Erro na requisição: ', err);
            });
    }
}

class FiltroSalas {
    constructor() {
        this.form = $('#frm-filtro');
        this.capacity = $('#in-pps');
        this.date = $('#in-dat');
        this.horaInicio = $('#in-hri');
        this.horaFim = $('#in-hrf');
        this.getButton = $('#bt-get');
        this.modalContent = $('.modal-content');
        this.defineFieldFormats();
        this.defineFormValidation();
        this.defineButtonBind();
    }

    defineFieldFormats() {
        this.date.datepicker({
            dateFormat: 'dd/mm/yy'
        });
        let timepickerConfig = {
            timeFormat: 'HH:mm',
            minTime: '07:00',
            maxTime: '22:00',
            defaultTime: '13:30',
            dynamic: false,
            dropdown: false,
            scrollbar: false
        };
        this.horaInicio.timepicker(timepickerConfig);
        this.horaFim.timepicker(timepickerConfig);
    }

    defineFormValidation() {
        this.form.validate({
            rules: {
                quantidade: {
                    required: true,
                    min: 0
                },
                data: {
                    required: true,
                    anyDate: true,
                    afterCurrentDay: true
                },
                inicio: {
                    required: true,
                    anyHour: true,
                    validHour: true,
                    competesWithOtherHour: ['inicio', 'final']
                },
                final: {
                    required: true,
                    anyHour: true,
                    validHour: true,
                    competesWithOtherHour: ['inicio', 'final']
                }
            }
        });
    }

    defineButtonBind() {
        let self = this;
        this.getButton.click(function () {
            if (self.form.valid()) {
                let capacidade = self.capacity.val();
                capacidade = parseInt(capacidade);
                let initialDate = self.date.datepicker("getDate");
                let finalDate = self.date.datepicker("getDate");
                let hora = self.horaInicio.val().substring(0, 2);
                let minuto = self.horaInicio.val().substring(3, 5);
                initialDate.setHours(parseInt(hora));
                initialDate.setMinutes(parseInt(minuto));
                hora = self.horaFim.val().substring(0, 2);
                minuto = self.horaFim.val().substring(3, 5);
                finalDate.setHours(parseInt(hora));
                finalDate.setMinutes(parseInt(minuto));
                var equipamentos = [];
                $('#in-eqp :checked').each(function () {
                    equipamentos.push(parseInt($(this).val()));
                });
                $.get('/salas/find', {
                    dataInicio: initialDate,
                    dataFim: finalDate,
                    capacidade: capacidade,
                    equipamentos: equipamentos
                })
                    .then(res => {
                        self.modalContent.html(res);
                    })
                    .fail(err => {
                        console.log(err);
                        self.modalContent.toggle();
                    });
            }
        });
    }
}

class AgendamentoSalas {
    constructor(container) {
        this.container = container;
        this.form = $('#frm-agendamento');
        this.descricao = $('#ag-des');
        this.sala = $('#ag-sl');
        this.name = $('#ag-nm');
        this.date = $('#ag-dat');
        this.horaInicio = $('#ag-hri');
        this.horaFim = $('#ag-hrf');
        this.postButton = $('#bt-post');
        this.modalContent = $('.modal-content');
        this.defineFieldFormats();
        this.defineFormValidation();
        this.defineButtonBind();
    }

    defineFieldFormats() {
        this.date.datepicker({
            dateFormat: 'dd/mm/yy'
        });
        let timepickerConfig = {
            timeFormat: 'HH:mm',
            minTime: '08:00',
            maxTime: '22:00',
            defaultTime: '13:30',
            dynamic: false,
            dropdown: false,
            scrollbar: false
        };
        this.horaInicio.timepicker(timepickerConfig);
        this.horaFim.timepicker(timepickerConfig);
    }

    defineFormValidation() {
        this.form.validate({
            rules: {
                sala: {
                    required: true
                },
                descricao: {
                    required: true
                },

                data: {
                    required: true,
                    anyDate: true,
                    afterCurrentDay: true
                },
                inicio: {
                    required: true,
                    anyHour: true,
                    validHour: true,
                    competesWithOtherHour: ['inicio', 'final']
                },
                final: {
                    required: true,
                    anyHour: true,
                    validHour: true,
                    competesWithOtherHour: ['inicio', 'final']
                }
            }
        });
    }

    defineButtonBind() {
        let self = this;
        this.postButton.click(function () {
            if (self.form.valid()) {
                let initialDate = self.date.datepicker("getDate");
                let finalDate = self.date.datepicker("getDate");
                let hora = self.horaInicio.val().substring(0, 2);
                let minuto = self.horaInicio.val().substring(3, 5);
                let descricao = self.descricao.val();
                initialDate.setHours(parseInt(hora));
                initialDate.setMinutes(parseInt(minuto));
                hora = self.horaFim.val().substring(0, 2);
                minuto = self.horaFim.val().substring(3, 5);
                finalDate.setHours(parseInt(hora));
                finalDate.setMinutes(parseInt(minuto));
                var idSala = parseInt($('#ag-sl option:selected').val());
                var usuarios = [];
                $('#ag-nm :checked').each(function () {
                    usuarios.push(parseInt($(this).val()));
                });
                $.post('/agendamento/adicionar', {
                    idSala: idSala,
                    descricao: descricao,
                    dataInicial: initialDate,
                    dataFinal: finalDate,
                    usuarios: usuarios
                })
                    .then(res => {
                        self.container.refreshPainelReunioes();
                        self.modalContent.html(res);
                    })
                    .fail(err => {
                        console.log(err);
                        self.modalContent.toggle();
                    });
            }
        });
    }
}