Flow 1
GET
http://localhost:8081/soapclient

Flow 2
POST
http://localhost:8081/multiclient
Input Data:
{
	"arg1": "Harish",
	"arg2": "Kumar"
}


Flow 3
POST
http://localhost:8081/xmlinput
Input Data:
<wsinput>
	<inputs>
		<argumentA>Harish</argumentA>
		<argumentB>Kumar</argumentB>
	</inputs>
</wsinput>


Flow 4
http://localhost:8081/nojaxb
Input Data:
<wsinput>
	<inputs>
		<argumentA>Harish</argumentA>
		<argumentB>Kumar</argumentB>
	</inputs>
</wsinput>
