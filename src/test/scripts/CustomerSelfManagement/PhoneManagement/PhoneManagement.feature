feature "Phone Management", {
    
    scenario "Create phone", {
        given "Logged in user"
        given "User is on the Create New Contact Information page"
        when "The user selects Phone number from the combobox and clicks the Create button"
        then "A form should appear, asking for phoneinformation"
        when "User fills every field correctly"
        then "Page should be redirected to the profile page, with the new phone number visible in the contact information section"
        when "User enters any incorrect value into any of the fields"
        then "Error message describing the error should be shown"
    }
    
    scenario "Edit phone", {
        given "Logged in user"
        given "User is on the Edit Contact Information page"
        when "User fills every field correctly"
        then "Page should be redirected to the profile page"
        and "with the updated phone number visible in the contact information section"
        and "the updated fields are truly updated"
        when "User enters any incorrect value into any of the fields"
        then "Error message describing the error should be shown"
    }
    
    scenario "Delete phone", {
        given "Logged in user"
        given "User is on the profile page"
        when "User clicks the remove button next to any of the phone numbers"
        then "The selected phone number should be removed from the contact information section"
    }
}

