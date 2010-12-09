feature "Customer Self Management", {

    in_order "to use webshop"
    as_a "customer"
    i_want "I want to manage my registration details"
    
    scenario "Open My Account", {
        given "Logged in Customer"
        when "User clicks the 'My Account' option on the main page"
        then "User profile page should appear"
    }
    
    scenario "Edit Personal Information", {
        given "Logged in user"
        given "User is on the Edit Personal Information page"
        when "User fills every mandatory field in the form"
        then "Page should be redirected to the profile screen"
        and  "the updated information should be showed"
        and "In the top-right corner, the username should change given it was changed by the user"
        
        when "User leaves a mandatory field empty"
        then "No information changes, error message describing the error should be showed"
    }

    scenario "Add new Animal", {
        given "Logged in user"
        given "User is on the Create new animal page"
        when "User fills every field correctly"
        then "A new animal related to the user should be created, with the animal's info available on the profile screen"
        when "User enters any incorrect value into any of the fields"
        then "Error message describing the error should be shown"
    }
    
    scenario "View gift card balance", {
        given "Logged in user"
        given "User is on the profile page"
        given "The user has a gift card with money available"
        when "User looks at the payment information section"
        then "The gift card should be listed, with the available amount shown"
    }
    
    scenario "View sent/received messages", {
        given "Logged in user"
        given "User is on the profile page"
        when "User clicks View Sent and Received link in the Messages section"
        then "A new page should appear, listing the sent and received messages of the user"
    }
    
    scenario "View order history and individual orders", {
        given "Logged in user"
        when "User clicks Order History link on the Main page"
        then "New page should appear, listing the orders of the user with the important information"
        when "User clicks any of the 'View' links"
        then "Order Status page should appear, detailing the order"
    }
}

