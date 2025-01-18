(function (document, $) {
    'use strict';
 var CHECKBOX_LIST = 'touch.checkbox';
 var FIELD5_LIST = 'touch.field5'
 var FIELD6_LIST = 'touch.field6';

$(document).on("foundation-contentloaded",function () {

 var $dialog = $(document).find('.cq-dialog');

 function showhideCheckbox() {
   var isChecked = false;
   var isTextfield = false;
   var el = $dialog.find("[data-listener='" + CHECKBOX_LIST + "']");
   if (el[0] !== undefined) {
 isChecked = el[0].checked;
   }
   if (isChecked === true) {
 $dialog.find("[data-listener='" + FIELD5_LIST + "']").parent().show();
 $dialog.find("[data-listener='" + FIELD6_LIST + "']").parent().hide();
   }  
   else {
 $dialog.find("[data-listener='" + FIELD5_LIST + "']").parent().hide();
 $dialog.find("[data-listener='" + FIELD6_LIST + "']").parent().show();
   }
}

  var checkBtn = $("[data-listener='" + CHECKBOX_LIST + "']").closest('.coral-Checkbox');
  if (checkBtn.length === 1) {
 showhideCheckbox();
 checkBtn.on('click', function () {
  showhideCheckbox();
 });
  }   
});
})(document,Granite.$);