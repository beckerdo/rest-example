# PUT or update JSON format entity to the host. 
# $1 is the index of the item to PUT/update
# The entity is stamped with a date.
curl -sv -X PUT --data "{\"content\":\"JSON PUT/updated at `date`\"}" --header "Content-Type: application/json" http://localhost:8080/rest/messages/$1