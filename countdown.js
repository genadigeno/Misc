TargetDate = "12/31/2019 11:59 pm";
BackColor = "white";
ForeColor = "gray";
CountActive = true;
CountStepper = -1;
LeadingZero = true;
DisplayFormat = "%%D%% Days, %%H%% Hours, %%M%% Minutes, %%S%% Seconds.";
FinishMessage = "It is finally here!";

function calcage(secs, num1, num2) {
	s = ((Math.floor(secs/num1))%num2).toString();
	if (LeadingZero && s.length < 2)
		s = "0" + s;
	return "<span style='font-size: 40px;'>" + "<span style='font-size: 20px;color: black'>" + s + "</span>" + "</span>";
}

function CountBack(secs) {
	if (secs < 0) {
		document.getElementById("cntdwn").innerHTML = FinishMessage;
		return;
	}
	DisplayStr = DisplayFormat.replace(/%%D%%/g, calcage(secs,86400,100000));
	DisplayStr = DisplayStr.replace(/%%H%%/g, calcage(secs,3600,24));
	DisplayStr = DisplayStr.replace(/%%M%%/g, calcage(secs,60,60));
	DisplayStr = DisplayStr.replace(/%%S%%/g, calcage(secs,1,60));

	document.getElementById("cntdwn").innerHTML = DisplayStr;
	if (CountActive)
		setTimeout("CountBack(" + (secs+CountStepper) + ")", SetTimeOutPeriod);
}

function putspan(backcolor, forecolor) {
	document.write("<span id='cntdwn' style='background-color:" + backcolor +
		"; color:" + forecolor + "'></span>");
}

if (typeof(BackColor)=="undefined")
	BackColor = "white";
if (typeof(ForeColor)=="undefined")
	ForeColor= "black";
if (typeof(TargetDate)=="undefined")
	TargetDate = "12/31/2020 5:00 AM";
if (typeof(DisplayFormat)=="undefined")
	DisplayFormat = "%%D%% Days, %%H%% Hours, %%M%% Minutes, %%S%% Seconds.";
if (typeof(CountActive)=="undefined")
	CountActive = true;
if (typeof(FinishMessage)=="undefined")
	FinishMessage = "";
if (typeof(CountStepper)!="number")
	CountStepper = -1;
if (typeof(LeadingZero)=="undefined")
	LeadingZero = true;


CountStepper = Math.ceil(CountStepper);
if (CountStepper == 0)
	CountActive = false;
var SetTimeOutPeriod = (Math.abs(CountStepper)-1)*1000 + 990;
putspan(BackColor, ForeColor);
var dthen = new Date(TargetDate);
var dnow = new Date();
if(CountStepper>0)
	ddiff = new Date(dnow-dthen);
else
	ddiff = new Date(dthen-dnow);
gsecs = Math.floor(ddiff.valueOf()/1000);
CountBack(gsecs);
//----------------------------------------------
// var countDownDate = new Date("2019-08-07 18:00").getTime();
    var countDownDate = new Date(mainElement.dataset.enddate + " " + mainElement.dataset.endtime).getTime();

    var x = setInterval(function() {

        var now = new Date().getTime();

        var distance = countDownDate - now;

        var days = Math.floor(distance / (1000 * 60 * 60 * 24));
        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        document.getElementById('day').innerHTML = days;
        document.getElementById('hour').innerHTML = hours;
        document.getElementById('minute').innerHTML = minutes;
        document.getElementById('seconds').innerHTML = seconds;

    }, 1000);
