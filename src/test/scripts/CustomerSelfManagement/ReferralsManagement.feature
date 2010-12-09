feature "Referrals Management", {
    
    scenario "View referred friends", {
        given "Logged in user"
        given "User is on the profile page"
        when "User clicks My Referrals in the Referrals section"
        then "A new page should appear listing the referred friends (if there is any)"
    }
    
    scenario "Send referral emails", {
        given "Logged in user"
        given "User is on the profile page"
        when "User clicks 'Referral Program' link in the Referrals section"
        then "A new page should appear, with some text inputs"
        when "User enters some email addresses and clicks 'Save'"
        when "The entered email address is not used by any user" 
        then "A message should appear: 'You have successfully sent a referral email to: <the entered email>'"
        when "The entered email address is already used by someone"
        then "An error message should appear describing the error"
    }
    
    scenario "Send referrals email at registration", {
        given "User has filled the User Registration page and he's on the 'Send referral emails' page"
        when "User enters some email addresses and clicks 'Save'"
        when "The entered email address is not used by any user" 
        then "A message should appear: 'You have successfully sent a referral email to: <the entered email>'"
        when "The entered email address is already used by someone"
        then "An error message should appear describing the error"
    }
}