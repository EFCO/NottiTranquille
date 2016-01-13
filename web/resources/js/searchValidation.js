/**
 * Created by Federico on 13/01/2016.
 */
var w = new Worker(validation())

function validation() {
    var nation = document.getElementById("nation").value();
    var city = document.getElementById("city").value();
    var checkin = document.getElementById("checkin").value();
    var checkout = document.getElementById("checkout").value();
    var alert = document.getElementById("alert");

    if (x.isEqual("") || city.isEqual("") || checkin.isEqual("") || checkin.isEqual("")) {
            alert.style.visibility = "visible"
        } else {
        alert.style.visibility = "none"

    }
}