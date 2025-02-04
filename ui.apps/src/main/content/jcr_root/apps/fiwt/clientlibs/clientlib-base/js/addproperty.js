if(document.getElementById("propertyImage")){

document.getElementById("propertyImage").addEventListener("change", function (event) {
            const file = event.target.files[0];
            const preview = document.getElementById("imagePreview");

            if (file) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    preview.innerHTML = `<img src="${e.target.result}" width="100" height="100" style="border-radius:5px;">`;
                };
                reader.readAsDataURL(file);
            } else {
                preview.innerHTML = "Preview";
            }
        });

        function submitForm() {
            
            var form = document.getElementById("propertyForm");
            var requiredFields = form.querySelectorAll("[required]");
            var isValid = true;
            requiredFields.forEach(function(input) {
                if (!input.value.trim()) {
                    // If the field is required and not filled, mark as invalid
                    input.style.borderColor = "red"; 
                    isValid = false;
                     // Highlight invalid field
                } else {
                    input.style.borderColor = "";  // Reset the border color if valid
                }
            });

            if (isValid) {
                 const formData = new FormData();
            const imageFile = $('#propertyImage')[0].files[0];

            formData.append("propertyImage", imageFile);
            formData.append("propertyTag", document.getElementById("propertyTag").value);
            formData.append("propertyType", document.getElementById("propertyType").value);
            formData.append("price", document.getElementById("price").value);
            formData.append("title", document.getElementById("title").value);
            formData.append("description", document.getElementById("description").value);
            formData.append("address", document.getElementById("address").value);
            formData.append("area", document.getElementById("area").value);
            formData.append("bedrooms", document.getElementById("bedrooms").value);
            formData.append("restrooms", document.getElementById("restrooms").value);
            formData.append("other", document.getElementById("other").value);
            formData.append("ownerName", document.getElementById("ownerName").value);
            formData.append("ownerContact", document.getElementById("ownerContact").value);
            formData.append("ownerEmail", document.getElementById("ownerEmail").value);
            formData.append("cityLocation", document.getElementById("cityLocation").value);
            formData.append("isNegotiable", document.getElementById("isNegotiable").checked);
            formData.append("discount", document.getElementById("discount").value);
            formData.append("verifiedBy", document.getElementById("verifiedBy").value);

                $.ajax({
                    type : "GET",
                    url : '/bin/addProperty.json',
                    data : formData,
                    cache : false,
                    processData: false,
                    contentType: false,
                    success : function(data, textStatus, jqXHR) {
                        alert(data);
                                },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        alert(errorThrown);
                    }
                });
                } else {
                    alert("Please fill in all required fields!");
                }

            
            
            
            
            
            
           

        }

        function clearForm() {
            document.getElementById("propertyForm").reset();
            document.getElementById("imagePreview").innerHTML = "Preview";
        }



document.getElementById("propertyImage").addEventListener("change", function (event) {
            const file = event.target.files[0];
            const preview = document.getElementById("imagePreview");

            if (file) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    preview.innerHTML = `<img src="${e.target.result}" width="100" height="100" style="border-radius:5px;">`;
                };
                reader.readAsDataURL(file);
            } else {
                preview.innerHTML = "Preview";
            }
        });

        function submitForm() {
            
            var form = document.getElementById("propertyForm");
            var requiredFields = form.querySelectorAll("[required]");
            var isValid = true;
            requiredFields.forEach(function(input) {
                if (!input.value.trim()) {
                    // If the field is required and not filled, mark as invalid
                    input.style.borderColor = "red"; 
                    isValid = false;
                     // Highlight invalid field
                } else {
                    input.style.borderColor = "";  // Reset the border color if valid
                }
            });

            if (isValid) {
                 const propertyTag = document.getElementById("propertyTag").value;
                const propertyType = document.getElementById("propertyType").value;
                const price = document.getElementById("price").value;
                const title = document.getElementById("title").value;
                const description = document.getElementById("description").value;
                const address = document.getElementById("address").value;
                const area = document.getElementById("area").value;
                const bedrooms = document.getElementById("bedrooms").value;
                const restrooms = document.getElementById("restrooms").value;
                const other = document.getElementById("other").value;
                const ownerName = document.getElementById("ownerName").value;
                const ownerContact = document.getElementById("ownerContact").value;
                const ownerEmail = document.getElementById("ownerEmail").value;
                const cityLocation = document.getElementById("cityLocation").value;
                const isNegotiable = document.getElementById("isNegotiable").checked;
                const discount = document.getElementById("discount").value;
                const verifiedBy = document.getElementById("verifiedBy").value;

                $.ajax({
                    type : "POST",
                    url : '/bin/addProperty.json',
                    data : {

                        title:title,
                        propertyType:propertyType,
                        propertyTag:propertyTag,
                        price:price,
                        description:description,
                        address:address,
                        area:area,
                        bedrooms:bedrooms,
                        restrooms:restrooms,
                        other:other,
                        ownerName:ownerName,
                        ownerContact:ownerContact,
                        ownerEmail:ownerEmail,
                        cityLocation:cityLocation,
                        isNegotiable:isNegotiable,
                        discount:discount,
                        verifiedBy:verifiedBy
                        },
                    success : function(data, textStatus, jqXHR) {
                        alert(data);
                                },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        alert(errorThrown);
                    }
                });
                } else {
                    alert("Please fill in all required fields!");
                }

            
            
            
            
            
            
           

        }

        function clearForm() {
            document.getElementById("propertyForm").reset();
            document.getElementById("imagePreview").innerHTML = "Preview";
        }

}
if(document.getElementsByClassName("property")){
    let boxes = document.getElementsByClassName("property");

    for (let i = 0; i < boxes.length; i++) {
        boxes[i].parentElement.className="propertyList";
        boxes[i].parentElement.parentElement.className="properties";
    }
    
}


