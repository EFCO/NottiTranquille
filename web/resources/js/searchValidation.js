/**
 * Created by Federico on 13/01/2016.
 */

function validation() {
    var nation = document.getElementById("nation").value();
    var city = document.getElementById("city").value();
    var checkin = document.getElementById("checkin").value();
    var checkout = document.getElementById("checkout").value();
    var alert = document.getElementById("alert");
    alert(nation,city,checkin,checkout);
    if (nation == null || city == null || checkin == null || checkout == null) {
            window.alert(nation);
            alert.style.display = "block";
            return false;
        } else {
        window.alert(nation);
        alert.style.display = "none";
        return true;
    }
}