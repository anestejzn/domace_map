
export function getImageForType(type: string) {
    switch(type) {
        case 'Jabuka':
            return './assets/images/products/apple.png';
        case 'Kupina':
            return './assets/images/products/blackberry.png';
        case 'Kupus':
            return './assets/images/products/cabbage.png';
        case 'Šargarepa':
            return './assets/images/products/carrot.png';
        case 'Višnja':
            return './assets/images/products/cherries.png';
        case 'Krastavac':
            return './assets/images/products/cucumber.png';
        case 'Paradajz':
            return './assets/images/products/tomato.png';
        case 'Luk':
            return './assets/images/products/onion.png';
        case 'Breskva':
            return './assets/images/products/peach.png';
        case 'Paprika':
            return './assets/images/products/pepper.png';
        case 'Šljiva':
            return './assets/images/products/plum.png';
        case 'Malina':
            return './assets/images/products/raspberries.png';
        case 'Pasulj':
            return './assets/images/products/beans.png';
        default: //jagoda
            return './assets/images/products/strawberry.png';
        
}
}
