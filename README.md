# CustomAccessLogValve
This is the project for Custome Access Log Valve for masking sensitive data fron request URIs which gets logged.

<!-- wp:paragraph -->
<p>Sometimes your log data contains some information or data that may be considered as sensitive. Some of the log messages may include user names, email addresses, URL
parameters, or any other personal information that you do not want to be disclosed to others.</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>Hiding/Masking the sensitive data in logs are got important due to the requirements of GDPR a European data protection regulation. In this GDPR role the "Data Controller"
for your logs, you should minimize the risk of exposing sensitive or personal data to the third parties. </p>
<!-- /wp:paragraph -->

# Summary:
<p>Step 1: Create a separate maven project</p>
<p>Step 2: Create a new Java class NameMaskingValve which extends AccessLogValve</p>
<p>Step 3: Add the Manifest file “MANIFEST.MF” in resources/META-INF folder to enable the JAR file to serve a purpose by allowing permissions.</p>
<p>Step 4: Build Maven Project</p>
<p>Step 5: Change the default valve configuration</p>

# Blog: 
http://insightsarch.com/2019/09/23/mask-sensitive-data-from-tomcat-access-logs/?preview=true
