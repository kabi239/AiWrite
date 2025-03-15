document.getElementById('essayForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const topic = document.getElementById('topic').value;
    const wordCount = document.getElementById('word_count').value;
    const noOfParagraphs = document.getElementById('no_of_paragraphs').value;
    const level = document.getElementById('level').value;

    const requestData = {
        topic: topic,
        word_count: wordCount,
        no_of_paragraphs: noOfParagraphs,
        level: level
    };

    try {
        const response = await fetch('http://localhost:8080/api/generate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const responseData = await response.json();
        document.getElementById('result').innerText = responseData.content;
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
        document.getElementById('result').innerText = 'Error generating essay. Please try again.';
    }
});