document.getElementById('planterForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const planter = {
        planterName: document.getElementById('planterName').value,
        lastName: document.getElementById('lastName').value,
        age: document.getElementById('age').value,
        country: document.getElementById('country').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        amountPlantedTree: document.getElementById('amountPlantedTree').value = 0
    };

    fetch('http://localhost:8080/planters', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(planter)
    })
        .then(response => {
            if (response.ok && response.headers.get('Content-Type')?.includes('application/json')) {
                return response.json();
            } else {
                return Promise.resolve({});
            }
        })
        .then(data => {
            console.log('Success:', data);
            loadPlanters();
        })
        .catch((error) => {
            console.error('Error:', error);
        });
});

document.getElementById('treeForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const tree = {
        treeName: document.getElementById('treeName').value,
        idPlanter: document.getElementById('idPlanter').value,
        plantationCity: document.getElementById('plantationCity').value,
        amountPlanted: document.getElementById('amountPlanted').value
    };

    fetch('http://localhost:8080/trees', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(tree)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            if (response.headers.get('Content-Type')?.includes('application/json')) {
                return response.json();
            } else {
                return Promise.resolve({});
            }


        })
        .then(data => {
            console.log('Tree created successfully :', data);
            loadPlanters();
        })
        .catch(error => {
            console.error('Error when creating tree:', error);
        });
});

function loadPlanters() {
    fetch('http://localhost:8080/planters')
        .then(response => response.json())
        .then(data => {
            const planterList = document.getElementById('planterList');
            planterList.innerHTML = '';

            data.forEach(planter => {
                const tr = document.createElement('tr');

                const nameTd = document.createElement('td');
                nameTd.textContent = `${planter.planterName} ${planter.lastName}`;
                tr.appendChild(nameTd);

                const countryTd = document.createElement('td');
                countryTd.textContent = `${planter.country}`;
                tr.appendChild(countryTd);

                const amountTd = document.createElement('td');
                amountTd.textContent = `${planter.amountPlantedTree}`;
                tr.appendChild(amountTd);

                planterList.appendChild(tr);
            });
        })
        .catch((error) => {
            console.error('Error loading planters:', error);
        });
}

loadPlanters();
