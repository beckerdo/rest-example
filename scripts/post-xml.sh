# POST an entity from a file to the host. 
# A post-template.xml is stamped with a date to post.xml.
cat post-template.xml | sed "s/POST/POST at `date`/" >post.xml
curl -sv -X POST --data @post.xml --header "Content-Type: application/xml" http://localhost:8080/rest/messages/