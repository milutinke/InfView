// Libraries
const JSONSchemaGenerator = require('json-schema-generator');
const fs = require('fs');

// Constants
const INPUT_FILE = '../../data/metascheme/Meta_Scheme.json';
const OUTPUT_FILE = '../../data/metascheme/Meta_Meta_Scheme.json';
const NPM_FOLDER = './node_modules';

// Functions
const deleteFolderRecursive = (path) => {
	if (fs.existsSync(path)) {
		fs.readdirSync(path).forEach(function(file, index){
			let currentPath = path + '/' + file;

			if (fs.lstatSync(currentPath).isDirectory())
				deleteFolderRecursive(currentPath);
			else fs.unlinkSync(currentPath);
		});

		fs.rmdirSync(path);
	}
};

const handleError = (error) => {
	console.log(error);
	deleteFolderRecursive(NPM_FOLDER);
};

// Check if the input file exists
if(!fs.existsSync(INPUT_FILE)) {
	handleError('File does not exists!');
    return;
}

// Read JSON from the input file
const metaScheme = fs.readFileSync(INPUT_FILE, 'utf8').toString();

// Check if we have any input
if(!metaScheme.length) {
	handleError('Empty file!');
	deleteFolderRecursive(NPM_FOLDER);
    return;
}

// Generate Meta Meta Scheme and write it to output file
fs.writeFileSync(OUTPUT_FILE, JSON.stringify(JSONSchemaGenerator(JSON.parse(metaScheme)), null, 2));
console.log('Successfully generated :)');
deleteFolderRecursive(NPM_FOLDER);