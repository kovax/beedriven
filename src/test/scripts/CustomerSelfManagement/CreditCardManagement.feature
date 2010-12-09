feature "Credit Card Management", {

    scenario "Create credit card", {
        given "Logged in user"
        given "User is on the Add New Credit Card page"
        when "User fills every field correctly"
        then "Page should be redirected to the profile page"
        and "the new Credit Card visible in the payment information section"
        
        when "User enters any incorrect value into any of the fields"
        then "Error message describing the error should be shown"
    }

    scenario "Edit credit card", {
        given "Logged in user"
        and "User is on the Edit Credit Card page"
        when "User fills every field correctly"
        then "Page should be redirected to the profile page"
        and "the updated credit card visible in the contact information section"
        and "the updated fields are truly updated"
        when "User enters any incorrect value into any of the fields"
        then "Error message describing the error should be showed"
    }

    scenario "Delete credit card", {
        given "Logged in user"
        given "User is on the profile page"
        when "User clicks the remove button next to any of the credit cards"
        then "The selected credit card should be removed from the payment information section"
    }
}