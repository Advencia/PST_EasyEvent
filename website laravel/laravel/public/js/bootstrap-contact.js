/* Bootstrap Contact Form
 ***************************************************************************/
$(document).ready(function(){
	// validate signup form on keyup and submit

	var validator = $("#contactform").validate({
		errorClass:'has-error',
		validClass:'has-success',
		errorElement:'div',
		highlight: function (element, errorClass, validClass) {
			$(element).closest('.form-control').addClass(errorClass).removeClass(validClass);
		},
		unhighlight: function (element, errorClass, validClass) {
			$(element).parents(".has-error").removeClass(errorClass).addClass(validClass);
		},
		rules: {
			contactname: {
				required: true,
				minlength: 2
			},
			email: {
				required: true,
				email: true
			},
			weburl: {
				required: true,
				url: true
			},
			phone: {
				required: true,
				phoneUS: true
			},
			subject: {
				required: true
			},
			message: {
				required: true,
				minlength: 10
			}
		},
		messages: {
			contactname: {
				required: '<span class="help-block">Entrez votre nom.</span>',
				minlength: jQuery.format('<span class="help-block">Votre nom doit contenir au moins {0} caractères.</span>')
			},
			email: {
				required: '<span class="help-block">Entrez une adresse email valide.</span>',
				minlength: '<span class="help-block">Entrez une adresse email valide.</span>'
			},
			weburl: {
				required: '<span class="help-block">Vous devez entrer une adresse de votre site web.</span>',
				url: jQuery.format('<span class="help-block">Vous devez entrer une URL valide.</span>')
			},
			phone: {
				required: '<span class="help-block">Vous devez entrer votre numéro de téléphone.</span>',
				phoneUS: jQuery.format('<span class="help-block">Vous devez entrer un numéro de téléphone valide.</span>')
			},
			subject: {
				required: '<span class="help-block">Vous devez entrer un sujet.</span>'
			},
			message: {
				required: '<span class="help-block">Vous devez entrer un message.</span>',
				minlength: jQuery.format('<span class="help-block">Entrez au moins {0} caractères.</span>')
			}
		}
	});
});
