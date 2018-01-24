	$(document).ready(function() {

			$('[id^=detail-]').hide();

			$('.toggle').click(function() {

				$input = $(this);

				$target = $('#' + $input.attr('data-toggle'));

				$target.slideToggle();

			});

			$('.filterinput').keyup(function() {

				var a = $(this).val();

				if (a.length >= 1) {

					// this finds all links in the list that contain the input,

					// and hide the ones not containing the input while showing the ones that do

					var containing = $('#list li').filter(function() {

						var regex = new RegExp('\\b' + a, 'i');

						return regex.test($('a', this).text());

					}).slideDown();

					$('#list li').not(containing).slideUp();

				} else {

					$('#list li').slideDown();

				}

				return false;

			})

		});