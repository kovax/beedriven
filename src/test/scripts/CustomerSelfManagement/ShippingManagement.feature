feature "", {
    
    scenario "Set default shipping destination address", {
        given "Logged in user"
        given "User is on the Profile page"
        when "User has more shipping addresses or has no default set"
        when "User clicks any of the available 'SET DEFAULT' links"
        then "The selected address becomes the default, 'Is default' text is showed in Contact Information"
    }
    
    scenario "Set default shipping method", { 
        given "Logged in user"
        given "User is on the Profile page"
        when "User selects a shipment method in the 'Default Shipment Method' section and clicks 'Set Default'"
        then "The selected option's radio button should stay checked after page reload"
    }
}