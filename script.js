let currentInput = "";
let previousInput = "";
let operation = null;
let memory = 0;

document.addEventListener("keydown", handleKeyPress);

function appendValue(value) {
    currentInput += value;
    updateDisplay();
}

function setOperation(op) {
    if (currentInput === "" && previousInput === "") return;
    if (previousInput !== "" && operation !== null) calculate();

    operation = op;
    previousInput = currentInput || previousInput;
    currentInput = "";
    updateOperationDisplay();
}
function factorial(n) {
    if (n < 0) return "Hata!"; // Negatif sayılar için faktöriyel yok
    if (n === 0 || n === 1) return 1; // 0! ve 1! = 1
    let result = 1;
    for (let i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}


function calculateSingle(op) {
    if (currentInput === "") return; // Eğer giriş yoksa işlem yapma

    const num = parseFloat(currentInput);
    let result;

    switch (op) {
        case "sqrt":
            result = Math.sqrt(num);
            break;
        case "factorial":
            result = factorial(num);
            break;
        case "log":
            result = Math.log10(num);
            break;
        case "ln":
            result = Math.log(num);
            break;
        case "sin":
            result = Math.sin(num * (Math.PI / 180)); // Dereceyi radyana çevir
            break;
        case "cos":
            result = Math.cos(num * (Math.PI / 180)); // Dereceyi radyana çevir
            break;
        default:
            return;
    }

    currentInput = result.toString();
    updateDisplay();
}
function calculate() {
    if (!operation || currentInput === "" || previousInput === "") return;

    const num1 = parseFloat(previousInput);
    const num2 = parseFloat(currentInput);
    let result;

    switch (operation) {
        case "add": result = num1 + num2; break;
        case "subtract": result = num1 - num2; break;
        case "multiply": result = num1 * num2; break;
        case "divide": 
            if (num2 === 0) {
                alert("Hata: Sıfıra bölme!");
                clearDisplay();
                return;
            }
            result = num1 / num2; break;
        case "percent": result = num1 * (num2 / 100); break;
        default: return;
    }

    currentInput = result.toString();
    operation = null;
    previousInput = "";
    updateDisplay();
    updateOperationDisplay();
}

function memoryAdd() {
    memory += parseFloat(currentInput || 0);
    displayNotification(`Bellek: ${memory}`);
}

function memorySubtract() {
    memory -= parseFloat(currentInput || 0);
    displayNotification(`Bellek: ${memory}`);
}

function memoryRecall() {
    currentInput = memory.toString();
    updateDisplay();
    updateOperationDisplay();
}

function memoryClear() {
    memory = 0;  // Belleği sıfırla
    displayNotification("Bellek sıfırlandı."); // Animasyonlu bildirim
    currentInput = "";  // Giriş alanını sıfırla
    updateDisplay();  // Ekranı güncelle
    updateOperationDisplay(); // İşlem ekranını da güncelle
}


function updateOperationDisplay() {
    const operationDisplay = document.getElementById("operation-display");
    operationDisplay.value = `${previousInput} ${getOperationSymbol(operation)} ${currentInput}`;
}

function updateDisplay() {
    document.getElementById("result-display").value = currentInput || "0";
}

function getOperationSymbol(op) {
    switch (op) {
        case "add": return "+"; 
        case "subtract": return "-"; 
        case "multiply": return "×"; 
        case "divide": return "÷"; 
        case "percent": return "%";
        default: return "";
    }
}
function displayNotification(message) {
    const notification = document.getElementById("notification");
    notification.textContent = message;
    notification.classList.add("show"); // "show" sınıfını ekle

    // 3 saniye sonra baloncuğu gizle
    setTimeout(() => {
        notification.classList.remove("show"); // "show" sınıfını kaldır
    }, 3000);
}


function handleKeyPress(event) {
    const key = event.key;
    if (!isNaN(key) || key === ".") appendValue(key);
    else if (key === "+") setOperation("add");
    else if (key === "-") setOperation("subtract");
    else if (key === "*") setOperation("multiply");
    else if (key === "/") setOperation("divide");
    else if (key === "Enter") calculate();
    else if (key === "Backspace") deleteLast();
    else if (key === "Escape") clearDisplay();
}

function deleteLast() {
    currentInput = currentInput.slice(0, -1);
    updateDisplay();
}

function clearDisplay() {
    currentInput = "";
    previousInput = "";
    operation = null;
    updateDisplay();
    updateOperationDisplay();
}

function displayNotification(message) {
    const notification = document.getElementById("notification");
    notification.textContent = message;
    notification.style.display = "block";
    setTimeout(() => {
        notification.style.display = "none";
    }, 2000);
}
