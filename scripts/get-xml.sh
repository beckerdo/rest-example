# GET an entity from the host. 
# $1 is the index of the item to GET
curl -sv -X GET http://localhost:8080/rest/messages/$1 --header "Accept: application/xml"
