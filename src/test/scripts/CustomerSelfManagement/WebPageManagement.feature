feature "Web Page Management", {
    
    scenario "Create web page", {
        given "Logged in user"
        given "User is on the Create New Contact Information page"
        when "The user selects 'Web Url/Address' from the combobox and clicks the Create button"
        then "A form should appear, asking for Web Url/Address"
        when "User fills every field correctly"
        then "Page should be redirected to the profile page, with the new Web Url/Address visible in the contact information section"
        when "User enters any incorrect value into any of the fields"
        then "Error message describing the error should be shown"
    }
    
    scenario "Edit web page", {
        given "Logged in user"
        given "User is on the Edit Contact Information page"
        when "User fills every field correctly"
        then "Page should be redirected to the profile page, with the updated Web Url/Address visible in the contact information section"
        when "User enters any incorrect value into any of the fields"
        then "Error message describing the error should be shown"
    }
    
    scenario "Delete web page", {
        given "Logged in user"
        given "User is on the profile page"
        when "User clicks the remove button next to any of the web urls/addresses"
        then "The selected web page should be removed from the contact information section"
    }
}
