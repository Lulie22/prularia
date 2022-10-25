var checkboxes = document.querySelectorAll("input[type='checkbox']")
console.log
var submitButt = document.getElementById("afgewerktKnop");

for (const checkbox of checkboxes) {
    checkbox.onclick = function() {
        const aantalCheckboxes = checkboxes.length;
        var aantalChecked = 0;
        for (const checkbox of checkboxes) {
            if (checkbox.checked) {aantalChecked++;}
        }
        submitButt.disabled = aantalChecked !== aantalCheckboxes;
    }
}