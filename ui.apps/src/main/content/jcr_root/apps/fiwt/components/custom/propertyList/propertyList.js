let browsemore = "";
    if (document.getElementById("browsemore")) {
        browsemore = document.getElementById("browsemore").innerHTML;
    }
    if (document.querySelectorAll(".getdata")) {
    		getPropertyData(null);
        document.querySelectorAll(".getdata").forEach(btn => {
            btn.addEventListener("click", function (event) {
                event.preventDefault(); // Prevents the default anchor action (optional)
                let filter = this.getAttribute("filter");
                if (filter == "-") {
                    filter = null;
                }
                getPropertyData(filter);
            });
        });
    }
    function getPropertyData(filter){
        $.ajax({
                    type: "GET",
                    url: '${resource.path @ context="scriptString"}' + '.json',
                    data: { filter: filter },

                    success: function (data, textStatus, jqXHR) {

                        let propertyList = "";
                        if (data != null) {

                            for (let i = 0; i < data.length; i++) {
                                propertyList += "<div class='col-lg-4 col-md-6 wow fadeInUp' data-wow-delay='0.1s' style='visibility: visible; animation-delay: 0.1s; animation-name: fadeInUp;'>" +
                                    "<div class='property-item rounded overflow-hidden'>" +
                                    "<div class='position-relative overflow-hidden'>" +
                                    "<a href=''><img class='img-fluid' src='" + data[i].propertyImage + "' alt=''></a>" +
                                    "<div class='bg-primary rounded text-white position-absolute start-0 top-0 m-4 py-1 px-3'> for " + data[i].propertyTag + "</div>" +
                                    "<div class='bg-white rounded-top text-primary position-absolute start-0 bottom-0 mx-4 pt-1 px-3'>" + data[i].propertyType + "</div>" +
                                    "</div>" +
                                    "<div class='p-4 pb-0'>" +
                                    "<h5 class='text-primary mb-3'>" + data[i].price + "</h5>" +
                                    "<a class='d-block h5 mb-2' href=''>" + data[i].title + "</a>" +
                                    "<p><i class='fa fa-map-marker-alt text-primary me-2'></i>" + data[i].address + "</p>" +
                                    "</div>" +
                                    "<div class='d-flex border-top'>" +
                                    "<small class='flex-fill text-center border-end py-2'><i class='fa fa-ruler-combined text-primary me-2'></i>" + data[i].area + " Sqft</small>" +
                                    "<small class='flex-fill text-center border-end py-2'><i class='fa fa-bed text-primary me-2'></i>" + data[i].bedrooms + " Bed</small>" +
                                    "<small class='flex-fill text-center py-2'><i class='fa fa-bath text-primary me-2'></i>" + data[i].restrooms + " Bath</small>" +
                                    "</div>" +
                                    "</div>" +
                                    "</div>";

                            }
                            document.getElementById("propertyListCards").innerHTML = "<div class='tab-pane fade show p-0 active'>" +
                                "<div class='row g-4'>" + propertyList + browsemore
                            "</div>" +
                                "</div>";

                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert(errorThrown);
                    }
                });
    }