
function validateBikeEditForm(){

    var name = document.bikeEditForm.name.value;

    if (name.length <1 || name.length > 15) {
        alert("Name must be between 1 and 15 characters long");
        return false;
    }
}