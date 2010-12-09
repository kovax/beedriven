feature "Address Management", {
    
    scenario "Create new address", {
        given "Logged in user"
        given "User is on the Create New Contact Information page"
        when "The user selects Postal Address from the combobox and clicks the Create button"
        then "A form should appear, asking for address information"
        when "User fills every field correctly"
        then "Page should be redirected to the profile page"
        and  "the new address visible in the contact information section"
        
        when "User enters any incorrect value into any of the fields"
        then "Error message describing the error should be shown"
    }
    
    scenario "Edit address", {
        given "Logged in user"
        given "User is on the Edit Contact Information page"
        when "User fills every field correctly"
        then "Page should be redirected to the profile page"
        and "the updated address visible in the contact information section"
        and "the updated fields are truly updated"
        
        when "User enters any incorrect value into any of the fields"
        then "Error message describing the error should be showed"
    }
    
    scenario "Delete address", {
        given "Logged in user"
        and "User is on the profile page"
        when "User clicks the remove button next to any of the addresses"
        then "The selected address should be removed from the contact information section"
    }
}
