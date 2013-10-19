/* 
 * Checking event capturing bubbling 
 * 
 * WARNING: This is bad using native JS API's, DON'T use!!!
 */

function test(){
    console.log("sdsad");  // This is for FireBug
}

// Old and bad style 
window.onload = function(){
    /* This uses the 
     * DOM Level 2 Event specification
     * style object.addEventListener() 
     * (probably not ok on IE)
    */
    if (document.addEventListener) {
        // Last param if bubble or not
        document.getElementById("A").addEventListener("click", traceEvent, true);
        document.getElementById("B").addEventListener("click", traceEvent, true);
        document.getElementById("C").addEventListener("click", traceEvent, true);
        document.getElementById("A").addEventListener("click", traceEvent, false);
        document.getElementById("B").addEventListener("click", traceEvent, false);
        document.getElementById("C").addEventListener("click", traceEvent, false);
    }
    else {
        document.getElementById("A").onclick = traceEvent;
        document.getElementById("B").onclick = traceEvent;
        document.getElementById("C").onclick = traceEvent;
    }
    document.getElementById("btnClear").addEventListener("click", clear,  false);
   
}

function traceEvent(event) {

    var phase, source;

    if (window.event) {
        target = window.event.srcElement.id;
        current = this.id
        if (this == window.event.srcElement)
            phase = "AT TARGET";
        else
            phase = "BUBBLE";
    }
    else {
        if (event.target.tagName)
            target = event.target.id;
        else
            target = "[Text]";
        current = event.currentTarget.id;
        if (event.eventPhase == Event.CAPTURING_PHASE)
            phase = "CAPTURE";
        else if (event.eventPhase == Event.BUBBLING_PHASE)
            phase = "BUBBLE";
        else if (event.eventPhase == Event.AT_TARGET)
            phase = "AT TARGET";
    }
    
    document.getElementById("traceOut").value +=
    "Target = " + target + "; Phase = " + phase + "; Current = " + current + ";\n";
}

function clear(){
    document.getElementById("traceOut").value = "";
}

