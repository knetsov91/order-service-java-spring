<h1 style="text-align: center">Order service documentation </h1>

<h2>Overview</h2>
<p>This project is part of <b>Restaurant management</b> project(<a href="https://github.com/knetsov91/restaurant">link</a>)
. It is implemented following REST architectural style and is consumed by other project synchronously via HTTP requests.
Information for orders is persisted in PostgreSQL RDBMS and interaction with it is made using Hibernate ORN and
Spring Data JPA. There are unit, integration and tests for the web layer. This project is dockerized for easier distribution and 
deployment.
</p>
<h2>Tech stack</h2>
<ul>
    <li>Java 17</li>
    <li>JUnit5</li>
    <li>Docker</li>
    <li>PostgreSQL</li>
    <li>H2 Database</li>
</ul>
<p>More details</p>
<ul>
    <li>Database </span><a href="./docs/database/database.md">Link</a></li>
    <li>Api </span><a href=".//docs/api/api.md">Link</a></li>
    <li>Security </span><a href="./docs/security/security.md">Link</a></li>
    <li>Tests </span><a href=".//docs/tests/test.md">Link</a></li>
</ul>