document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("essayForm");
    const resultDiv = document.getElementById("result");

    form.addEventListener("submit", async function (event) {
        event.preventDefault();

        const topic = document.getElementById("topic").value.trim();
        const wordCount = document.getElementById("word_count").value.trim();
        const noOfParagraphs = document.getElementById("no_of_paragraphs").value.trim();
        const level = document.getElementById("level").value;

        if (!topic) {
            resultDiv.innerText = "Please enter a topic.";
            return;
        }

        resultDiv.innerText = "Generating essay... Please wait.";

        const requestData = {
            topic: topic,
            word_count: wordCount || null,
            no_of_paragraphs: noOfParagraphs || null,
            level: level
        };

        try {
            const response = await fetch("http://localhost:8080/api/generate", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(requestData)
            });

            if (!response.ok) {
                throw new Error("Failed to generate essay.");
            }

            const responseData = await response.json();
            resultDiv.innerText = responseData.content || "Essay generated but content is empty.";
        } catch (error) {
            console.error("Fetch error:", error);
            resultDiv.innerText = "Error generating essay. Please try again.";
        }
    });
});