# familytree

Here's my attempt to use d4js to represent my family tree.

A spreadsheet exists that hopefully everyone can understand. Here's and example:

ID,Mother ID,Father ID,First Name,Middle Name,Last Name,Gender,Place of Birth,Generation??
1,,,mum,,smith,female,JA,
2,,,dad,,smith,male,JA,
3,1,2,son,,smith,male,JA,

<ol>
	<li>ID - must be unique</li>
	<li>Mother ID - references another row who is the mother</li>
	<li>Father ID - references another row who is the father</li>
	<li>First Name</li>
	<li>Middle Name</li>
	<li>Family Name</li>
	<li>Other columns are gravey, they provide additional person information</li>
</ol>  


This is then converted to a JSON representation for the dj3 tree layout
http://bl.ocks.org/d3noob/8375092

It's a bit crude now cause the JSON is just dropped into the html file yesfamily.html.


See also 

d3 2-way tree http://bl.ocks.org/kanesee/5d6c48bffd4ea31201fb

https://chezsoi.org/lucas/genealogic-d3/skywalker.html
https://github.com/Lucas-C/genealogic-d3

https://github.com/mbostock/d3/wiki/Tree-Layout

#my notes
<a href="http://bl.ocks.org/mbostock/2966094" target='_blank'>Pedigree Tree</a>


<a href="http://stackoverflow.com/questions/13763352/how-do-i-show-marriages-in-a-d3-js-based-family-tree" target='_blank'>
How do I show marriages in a d3.js based 'family-tree'</a>

<a href="http://d3js.org/" target='_blank'>http://d3js.org/</a>

<a href="http://stackoverflow.com/questions/31245751/how-do-you-create-a-family-tree-in-d3-js" target='_blank'>
How do you create a family tree in d3.js? </a>

<a href="http://www.d3noob.org/2014/01/tree-diagrams-in-d3js_11.html" target='_blank'>
D3.js Tips and Tricks: Tree diagrams in d3.js</a>


<a href="http://stackoverflow.com/questions/23468831/set-up-a-html-page-with-left-and-right-panel" target='_blank'>
http://stackoverflow.com/questions/23468831/set-up-a-html-page-with-left-and-right-panel </a>

<a href="http://stackoverflow.com/questions/24606854/want-inner-div-to-scroll-in-left-panel-with-100-height-and-footer" target='_blank'>
http://stackoverflow.com/questions/24606854/want-inner-div-to-scroll-in-left-panel-with-100-height-and-footer</a>    