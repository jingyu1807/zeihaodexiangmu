
var srt;

var Srt = function(url) {

    if(!url) return;

    srt = this;

    polyvObject.ajax({
        url:url,
        type:'GET',
        dataType:'text',
        success: function(data){
            srt.srtContent = data;
            srt.lines = [];
            srt.init();
        }
    });

    return srt;
};

Srt.prototype = {
    b: 0,
    currentTime:0,
    showSrt:"on",
    init: function() {
        this.parse();
    },
    parse: function() {
        var lines = this.srtContent.split('\n\r\n');
        for (var i = 0; i < lines.length; i++) {
            var line = lines[i];
            var origin = line.split('\n');
            if(origin[0].length==1){
                origin.splice(0,1);
            }
            if (origin.length >= 3){
                // counter
                var counter = origin[0];
                // time
                var timeLine = origin[1];
                var startText = timeLine.match(/^[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]/)[0];
                var endText = timeLine.match(/\s[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]/)[0].replace(' ', '');
                var startDate = this.stringToDate(startText);
                var endDate = this.stringToDate(endText);
                // subtitle 
                var subtitle = '';
                for (var j = 2; j < origin.length; j++) {
                    //subtitle = subtitle + origin[j] + '\n';
                    subtitle = subtitle + origin[j] + '<br'+'>';
                }
                // push to list
                this.lines.push({
                    counter: counter,
                    subtitle: subtitle,
                    start: this.dateToObject(startDate),
                    end: this.dateToObject(endDate),
                    startTime: this.stringToSeconds(startText),
                    endTime: this.stringToSeconds(endText)
                });
            }
        }
    },
    shift: function(delta, unit) {
        var time, get;
        switch (unit) {
            case 'hours':
            {
                time = 1000 * 60 * 60;
                break;
            }
            case 'minutes':
            {
                time = 1000 * 60;
                break;
            }
            case 'seconds':
            {
                time = 1000;
                break;
            }
            case 'milliseconds':
            {
                time = 1;
                break;
            }
        }
        for (var i = 0; i < this.lines.length; i++) {
            var line = this.lines[i];
            var newStartTime = new Date(line.start.time.getTime() + delta * time);
            var newEndTime = new Date(line.end.time.getTime() + delta * time);
            this.updateLineTime(i, newStartTime, newEndTime);
            var newSTime = line.startTime + delta*(time/1000);
            var newETime = line.endTime + delta*(time/1000);
            this.updateLineStartEndTime(i, newSTime, newETime);
        }
        // update content
        this.updateSrtContent();
    },
    updateLineTime: function(n, newStartTime, newEndTime) {
        var line = this.lines[n];
        this.lines[n] = {
            counter: line.counter,
            subtitle: line.subtitle,
            start: this.dateToObject(newStartTime),
            end: this.dateToObject(newEndTime),
            startTime: line.startTime,
            endTime: line.endTime
        }
    },
    updateLineStartEndTime: function(n, newStartTime, newEndTime){
        var line = this.lines[n];
        this.lines[n] = {
            counter: line.counter,
            subtitle: line.subtitle,
            start: line.start,
            end: line.end,
            startTime: newStartTime,
            endTime: newEndTime
        }
    },
    updateSrtContent: function() {
        var srt = '';
        for (var i = 0; i < this.lines.length; i++) {
            var line = this.lines[i];
            srt += line.counter + '\n' +
            line.start.text + ' --> ' + line.end.text + '\n' +
            line.subtitle + '\n\r\n';
        };
        this.srtContent = srt;
    },
    getSrtContent: function() {
        return this.srtContent;
    },
    // helper functions
    // not main methods
    // used to make everything simple
    stringToDate: function(string) {
        // turn string format like "00:12:42,321" to date
        var firstColonIndex = string.indexOf(':');
        var secondColonIndex = this.nthChar(string, ':', 2);
        var commaIndex = string.indexOf(',');
        var hour = string.substring(0, firstColonIndex);
        var minute = string.substring(firstColonIndex + 1, secondColonIndex);
        var second = string.substring(secondColonIndex + 1, commaIndex);
        var msecond = string.substring(commaIndex + 1);
        return new Date(1970, 1, 1, hour, minute, second, msecond);
    },
    stringToSeconds:function(string){
        //turn string format like "00:12:42,321" to seconds
        var msecond = string.split(",")[1];
        var stringPure = string.substr(0,string.indexOf(","));
        var second = parseInt(stringPure.split(":")[2]);
        var minute = parseInt(stringPure.split(":")[1]*60);
        var hour = parseInt(stringPure.split(":")[0]*3600);
        var time = parseFloat((second + minute + hour) + "."+ msecond);
        return time;
    },
    dateToObject: function(date) {
        return {
            text: (date.getHours() < 10 ? '0' : '') + date.getHours() + ':' + (date.getMinutes() < 10 ? '0' : '') + date.getMinutes() + ':' + (date.getSeconds() < 10 ? '0' : '') + date.getSeconds() + ',' + (date.getMilliseconds() < 10 ? '00' : '') + ((date.getMilliseconds() < 100 && date.getMilliseconds() >= 10) ? '0' : '') + date.getMilliseconds(),
            time: date,
            hours: date.getHours(),
            minutes: date.getMinutes(),
            seconds: date.getSeconds(),
            milliseconds: date.getMilliseconds()
        }
    },
    nthChar: function(string, character, n) {
        // find the index of the nth char in string
        var count = 0,
            i = 0;
        while (count < n && (i = string.indexOf(character, i) + 1)) {
            count++;
        }
        if (count == n) return i - 1;
        return NaN;
    },
    time: function(a){
        this.currentTime = a;
    },
    startTimer: function(){
        this.b = window.setInterval(function(){

            var findIndex = -1;
            var findContent = "";

            if(srt.lines!=undefined){
                if(srt.lines.length>0 && srt.showSrt=="on"){
                    for(var i = 0;i<srt.lines.length;i++){
                        if(srt.currentTime>=srt.lines[i].startTime && srt.currentTime<srt.lines[i].endTime){
                            findIndex = i;
                            findContent = srt.lines[i].subtitle;
                            break;
                        }
                    }
                }

                if(findIndex>=0)
                {
                    srt.container.html(findContent);

                }else
                {
                    srt.container.html(" ");
                }
            }

        },1000)
    },
    stopTimer: function(){
        window.clearInterval(this.b);
    },
    show: function(){
        if(this.container)
        {
            this.container.css("display","block");
        }

    },
    hide: function(){
        if(this.container)
        {
            this.container.css("display","none");
        }

    },
    setContainer: function(a){
        this.container = a;
    }
};

