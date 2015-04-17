var Conclave = (function () {
    var buArr = [], arlen;
    return {
        init: function () {
            this.addCN();
            this.clickReg();
        },
        addCN: function () {
            var buarr = ["holder_bu_awayL4", "holder_bu_awayL3", "holder_bu_awayL2", "holder_bu_awayL1", "holder_bu_center", "holder_bu_awayR1", "holder_bu_awayR2", "holder_bu_awayR3", "holder_bu_awayR4"];
            for (var i = 1; i <= buarr.length; ++i) {
                $("#bu" + i).removeClass().addClass(buarr[i - 1] + " holder_bu");
            }
        },
        move: function (cpos, mpos) {
            if (cpos != mpos) {
                tomove = cpos > mpos ? arlen - cpos + mpos : mpos - cpos;
                while (tomove) {
                    var t = buArr.shift();
                    buArr.push(t);
                    for (var i = 1; i <= arlen; ++i) {
                        $("#bu" + i).removeClass().addClass(buArr[i - 1] + " holder_bu");
                    }
                    --tomove;
                }
            }
        },
        clickReg: function () {
            var self = this;
            var $holders = $(".holder_bu");
            $holders.each(function () {
                buArr.push($(this).attr('class'))
            });
            arlen = buArr.length;
            for (var i = 0; i < arlen; ++i) {
                buArr[i] = buArr[i].replace(" holder_bu", "")
            }

            $(".controls .previous, .controls .next").on("click", function () {
                var cpos, mpos = buArr.indexOf("holder_bu_center");
                if ($(this).hasClass('next')) {
                    cpos = mpos + 1;
                } else if ($(this).hasClass('previous')) {
                    cpos = mpos - 1;
                }
                self.move(cpos, mpos);
            });
        },
        auto: function () {
            for (i = 1; i <= 1; ++i) {
                $(".holder_bu").delay(4000).trigger('click', "bu" + i).delay(4000);
            }
        }
    };
})();

$(document).ready(function () {
    window['conclave'] = Conclave;
    Conclave.init();
})