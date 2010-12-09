feature "Email Management", {
    
    scenario "Create email", {
        given "Logged in user"
        given "User is on the Create New Contact Information page"
        when "The user selects email address from the combobox and clicks the Create button"
        then "A form should appear, asking for email address"
        when "User fills every field correctly"
        then "Page should be redirected to the profile page, with the new email address visible in the contact information section"
        when "User enters any incorrect value into any of the fields"
        then "Error message describing the error should be shown"
    }
    
    scenario "Edit email", {
        given "Logged in user"
        given "User is on the Edit Contact Information page"
        when "User fills every field correctly"
        then "Page should be redirected to the profile page, with the updated email address visible in the contact information section"
        when "User enters any incorrect value into any of the fields"
        then "Error message describing the error should be shown"
    }
    
    scenario "Delete email", {
        given "Logged in user"
        given "User is on the profile page"
        when "User clicks the remove button next to any of the email addresses"
        then "The selected email address should be removed from the contact information section"
    }
    
    scenario "Set default email language", {
        given "Logged in user"
        given "User is on the User Profile page"
        when "User selects a language from the combobox"
        then "Default language of emails should change. Language of emails received by user should change according to the change"
    }
}
