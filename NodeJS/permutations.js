let word = process.argv[2];
for (let i = word.length; i > 0 ; i--){
    console.log(word.substring(i,word.length) + word.substring(0,i));
}
