/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$.validator.addMethod("anyDate",
    function (value, element) {
        return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[/., -](0?[1-9]|1[0-2])[/., -](19|20)?\d{2}$/);
    },
    "Por favor, insira uma data válida."
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

class AgendamentoSalas {
    constructor() {
        this.form = $('#frm-agendamento');
        this.capacity = $('#ag-nm');
        this.date = $('#ag-dat');
        this.horaInicio = $('#ag-hri');
        this.horaFim = $('#ag-hrf');
        this.getButton = $('#bt-post');
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
                    anyDate: true
                },
                inicio: {
                    required: true,
                    anyHour: true,
                    validHour: true
                },
                final: {
                    required: true,
                    anyHour: true,
                    validHour: true
                }
            }
        });
    }

    defineButtonBind() {
        let self = this;
        this.getButton.click(function () {
            if (self.form.valid()) {
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
                var salas = [];
                $('#ag-nm :checked').each(function () {
                    salas.push(parseInt($(this).val()));
                });
                $.post('/agendamento/adicionar', {
                    nomesala: nameSala
                    dataInicio: initialDate,
                    dataFim: finalDate,
                    capacidade: capacidade,
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
