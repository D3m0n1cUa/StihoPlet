 $(function(){
	 $('.rifma').click(function() {
		 var id = event.target.id.substring(4);
		 window.location.href = "../rifma/" + id;
	 });
	    	
	 $('.button-reply').click(function() {
		 var id = event.target.id.substring(4);
		 $('#titleModalRifma').text("Rime to rhyme");
		 $('#idParentRifma').attr('value', id);
	 });
	 
	 $('#newRifmaModal').on('hidden.bs.modal', function () {
		 $('#titleModalRifma').text("Add rime");
		 $('#idParentRifma').attr('value', null);
		 $('#inputRifma').val('');
		 $('#hintRifm').text("60 characters remain.");
	 });
	    	
	 $('#newRifmaModal').on('shown.bs.modal', function() {
		 $(this).find('[autofocus]').focus();
		 
	 });
});
		
function onChangeRifma(rifma) {
	var len = rifma.length;
	var button = document.getElementById('writeRifm');
	var text = document.getElementById('hintRifm');
	text.innerHTML = (60 - len) + " characters remain.";
	if(len > 0 && len <= 60) {
		button.removeAttribute("disabled");
	} else {
		button.setAttribute("disabled","disabled");
	}
}