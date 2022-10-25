var checkboxes = document.querySelectorAll("input[type='checkbox']")
console.log
var submitButt = document.getElementById("afgewerktKnop");

for (const checkbox of checkboxes) {
    checkbox.onclick = async function () {
        // checking if all checkboxes are checked
        const aantalCheckboxes = checkboxes.length;
        var aantalChecked = 0;
        for (const checkbox of checkboxes) {
            if (checkbox.checked) {
                aantalChecked++;
            }
        }
        submitButt.disabled = aantalChecked !== aantalCheckboxes;
        // sending the checked attribute to the server for updates
        const data = {
            magazijnplaatsid: checkbox.dataset.magazijnplaatsid,
            status: this.checked
        }
        const response = await fetch("checkbox",
            {method: "POST", body:JSON.stringify(data), mode:"no-cors", headers: { "Content-Type": "application/json" },})
        // via path variable (niet mogelijk gezien deze altijd wordt doorgestuurd als GET en niet als POST
        /*console.log(checkbox.dataset.magazijnplaatsid)
        console.log(checkbox.checked)
        const url = "localhost:8080/bestelling/checkbox/" + checkbox.dataset.magazijnplaatsid + "/" + checkbox.checked
        const response = await fetch(url)
        if (response.ok) {
            console.log("Fetch is goed verlopen")
        } else {
            console.log("Fetch is niet goed verlopen")
        }*/
    }
}

// opslaan van checkboxValues in localStorage
/*
var checkboxValues = JSON.parse(localStorage.getItem('checkboxValues');
if (checkboxValues === null){
    checkboxValues = {};
} else {
    //checkboxes.forEach()
}
*/
