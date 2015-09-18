"""
Animal Shelter:

An animal shelter holds only dogs and cats. People must adopt either the “oldest” animals,
or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of that type).
They cannot select which specific animal they would like.

Create the data structures to maintain this system and implement operations such as
enqueue, dequeueAny, dequeueDog and dequeueCat.
You may use the built-in LinkedList data structure.

"""

from python.chapter2.datastructures import *


class Animal:
    def __init__(self, name):
        self.name = name


class Cat(Animal):
    def __str__(self):
        return 'Cat %s says meaw' % self.name


class Dog(Animal):
    def __str__(self):
        return 'Dog %s says woof' % self.name


class AnimalShelter:
    def __init__(self):
        self.nextAnimal = None
        self.nextDog = None
        self.nextCat = None
        self.lastAnimal = None

    def enqueue(self, animal):
        if animal is None:
            return
        if not isinstance(animal, Animal):
            raise TypeError('We can shelter animals only. Sorry plants, fungi, bacteria and archea')
        node = ListNode(animal)
        if self.nextAnimal is None:
            self.nextAnimal = node
        else:
            self.lastAnimal.next = node
        self.lastAnimal = node
        if self.nextDog == None and isinstance(animal, Dog):
            self.nextDog = node
        if self.nextCat == None and isinstance(animal, Cat):
            self.nextCat = node

    def findNext(self, animalClass, startNode):
        ptr = startNode
        while ptr is not None:
            if isinstance(ptr.value, animalClass):
                return ptr
            ptr = ptr.next

    def dequeueAny(self):
        if self.nextAnimal is not None:
            rv = self.nextAnimal
            self.nextAnimal = self.nextAnimal.next
            if self.nextAnimal is None:
                self.nextDog = self.nextCat = self.lastAnimal = None
            if self.nextCat == rv:
                self.nextCat = self.findNext(Cat, self.nextAnimal)
            elif self.nextDog == rv:
                self.nextDog = self.findNext(Dog, self.nextAnimal)
            return rv.value

    def dequeueCat(self):
        if self.nextCat is not None:
            # is the next available animal a cat?
            if self.nextCat == self.nextAnimal:
                return self.dequeueAny()
            # no, the first cat is somewhere farther along the queue
            rv = self.nextCat.value
            node = self.nextCat
            self.nextCat = self.findNext(Cat, self.nextCat.next)
            self.deleteNode(node)

            return rv

    def dequeueDog(self):
        if self.nextDog is not None:
            # is the next available animal a cat?
            if self.nextDog == self.nextAnimal:
                return self.dequeueAny()
            # no, the first cat is somewhere farther along the queue
            rv = self.nextDog.value
            node = self.nextDog
            self.nextDog = self.findNext(Dog, self.nextDog.next)
            self.deleteNode(node)

            return rv

    def deleteNode(self, node):
        if self.nextAnimal is not None and node is not None:
            ptr = self.nextAnimal
            while ptr is not None:
                if ptr.next == node:
                    ptr.next = ptr.next.next
                    return
                ptr = ptr.next


import unittest
import random

random.seed()


class TestAnimalShelter(unittest.TestCase):
    def testDequeueAny(self):
        dogNames = ['Raplh', 'Cecil', 'Jeffrey', 'Rick', 'Larry', 'Simon', 'Fido']
        catNames = ['Anna', 'Maria', 'Celeste', 'Gloria', 'Yvonne', 'Elisabeth', 'Maude']

        allAnimals = [Dog(n) for n in dogNames]
        allAnimals.extend([Cat(n) for n in catNames])
        random.shuffle(allAnimals)
        myDogs = [a.name for a in allAnimals if isinstance(a, Dog)]
        myCats = [a.name for a in allAnimals if isinstance(a, Cat)]
        allNames = [a.name for a in allAnimals]

        shelter = AnimalShelter()
        for a in allAnimals:
            shelter.enqueue(a)

        newNames = []
        a = shelter.dequeueAny()
        while a is not None:
            newNames.append(a.name)
            a = shelter.dequeueAny()

        self.assertEqual(allNames, newNames)

    def testDequeueDog(self):
        dogNames = ['Raplh', 'Cecil', 'Jeffrey', 'Rick', 'Larry', 'Simon', 'Fido']
        catNames = ['Anna', 'Maria', 'Celeste', 'Gloria', 'Yvonne', 'Elisabeth', 'Maude']

        allAnimals = [Dog(n) for n in dogNames]
        allAnimals.extend([Cat(n) for n in catNames])
        random.shuffle(allAnimals)
        myDogs = [a.name for a in allAnimals if isinstance(a, Dog)]
        myCats = [a.name for a in allAnimals if isinstance(a, Cat)]
        allNames = [a.name for a in allAnimals]

        shelter = AnimalShelter()
        for a in allAnimals:
            shelter.enqueue(a)

        newNames = []
        a = shelter.dequeueDog()
        while a is not None:
            newNames.append(a.name)
            a = shelter.dequeueDog()

        self.assertEqual(myDogs, newNames)

        newNames = []
        a = shelter.dequeueAny()
        while a is not None:
            newNames.append(a.name)
            a = shelter.dequeueAny()

        self.assertEqual(myCats, newNames)

    def testDequeueCat(self):
        dogNames = ['Raplh', 'Cecil', 'Jeffrey', 'Rick', 'Larry', 'Simon', 'Fido']
        catNames = ['Anna', 'Maria', 'Celeste', 'Gloria', 'Yvonne', 'Elisabeth', 'Maude']

        allAnimals = [Dog(n) for n in dogNames]
        allAnimals.extend([Cat(n) for n in catNames])
        random.shuffle(allAnimals)
        myDogs = [a.name for a in allAnimals if isinstance(a, Dog)]
        myCats = [a.name for a in allAnimals if isinstance(a, Cat)]
        allNames = [a.name for a in allAnimals]

        shelter = AnimalShelter()
        for a in allAnimals:
            shelter.enqueue(a)

        newNames = []
        a = shelter.dequeueCat()
        while a is not None:
            newNames.append(a.name)
            a = shelter.dequeueCat()

        self.assertEqual(myCats, newNames)

        newNames = []
        a = shelter.dequeueAny()
        while a is not None:
            newNames.append(a.name)
            a = shelter.dequeueAny()

        self.assertEqual(myDogs, newNames)
