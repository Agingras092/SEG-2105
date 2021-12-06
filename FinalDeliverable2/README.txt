1. When creating a user, rank 0 is for an admin user, rank 1 is for an instructor user.
2. No data validation on time of day when adding/editing
3. FirstFragment and SecondFragment are there by mistake I believe.
4. A final read of the outline makes me think I should have allowed the instructor to see ALL classes, and not only the ones they have scheduled.
   Fixed by calling getAllClasses() instead of getAllClassesByInst() line 60 in InstructorMainActivity.java...

	JUnit testing wasn't working properly. Looking into the causes, but not enough time before submission.

	Test 1 - Verify database name comparissons (i.e. Cant have 2 classes with the same name. This would be tested by
		 running the verifyClassName db function on something non exisstant, then adding a class and testing again
		 with the same name.
	Test 2 - Field validations on account creations. Asserting a name with characters, then one with numbers. Expected TRUE then FALSE
	Test 3 - Creating a fitness type. First instance should allow any fitness type (including names with numbers), however another consecutive
		 creation of that type with the same name will be rejected.
	Test 4 - Attempting an email on account add without the proper email format. "a@gmail.com" vs "a.gmail@com"
	Test 5 - Running the setup() function in EditClassInformation with an inserted parameter instead of fetching it from classnameholder.
		 Forcing an invalid parameter should leave the fields blank on page load.