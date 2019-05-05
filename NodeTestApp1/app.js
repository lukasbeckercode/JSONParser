'use strict';
const v = require('vaxic')
const path = require('path')
const fs = require('fs')
const app = new v() 

const quoteFileContent = fs.readFileSync(path.join(__dirname, 'test.txt')).toString()

const quotes = []

quoteFileContent.split('\n').forEach((line) => {
    const lineParts = line.split('---')
    quotes.push({
        'qoute': lineParts[0],
        'by': lineParts[1]
    })
})

app.add('GET', '/api/quote', (req, res) => {
    res.writeHead(200, { 'Content-Type': 'application/json' })
    const randomQuote = quotes[Math.floor(Math.random() * quotes.length)]
    res.end(JSON.stringify(randomQuote))
}
)
app.add((req, res) => {
    res.writeHead(404, { 'Content-Type':'application/json'})
    res.end(JSON.stringify({
        'error': 'resource not found'
    }))
})
app.listen(8080, () => {
    console.log("Listening")
})
