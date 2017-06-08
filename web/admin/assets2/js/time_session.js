var mimtimeout = 10;
            var segtimeout = 0;
            function time()
            {
                segtimeout = segtimeout -1;
                if(segtimeout < 0){
                    segtimeout = 59;
                    mimtimeout = mimtimeout - 1;
                }
                if (segtimeout >= 0 && segtimeout < 10) {
                    segtimeout = "0" + segtimeout;
                }
                if(mimtimeout === 0){
                    location.href = "/sigbase/LogOff.jsp";
                }
                today = new Date();
                h = today.getHours();
                if (h >= 0 && h < 10) {
                    h = "0" + h;
                }
                m = today.getMinutes();
                if (m >= 0 && m < 10) {
                    m = "0" + m;
                }
                s = today.getSeconds();
                if (s >= 0 && s < 10) {
                    s = "0" + s;
                }
                document.getElementById('txt').innerHTML = h + ":" + m + ":" + s;
                document.getElementById('timeout').innerHTML ="Sessão expira em  "+ mimtimeout + ":" + segtimeout;
                setTimeout('time()', 1000);
            }


