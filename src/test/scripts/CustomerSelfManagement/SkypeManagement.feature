feature "Skype Management", {
    
    scenario "Create skype", {
        given "Logged in user"
        given "User is on the Create New Contact Information page"
        when "The user selects 'Skype' from the combobox and clicks the Create button"
        then "A form should appear, asking for Skype name"
        when "User fills every field correctly"
        then "Page should be redirected to the profile page, with the new Skype name visible in the contact information section"
        when "User enters any incorrect value into any of the fields"
        then "Error message describing the error should be shown"
    }
    
    scenario "Edit skype", {
        given "Logged in user"
        given "User is on the Edit Contact Information page"
        when "User fills every field correctly"
        then "Page should be redirected to the profile page, with the updated skype name visible in the contact information section"
        when "User enters any incorrect value into any of the fields"
        then "Error message describing the error should be shown"
    }
    
    scenario "Delete skype", {
        given "Logged in user"
        given "User is on the profile page"
        when "User clicks the remove button next to any of the skype names"
        then "The selected skype name should be removed from the contact information section"
    }
}
